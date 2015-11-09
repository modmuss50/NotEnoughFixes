package me.modmuss50.nef;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

public class NEFClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (name.equals("codechicken.nei.recipe.FurnaceRecipeHandler")) {
            ClassNode classNode = readClassFromBytes(basicClass);
            MethodNode method = findMethodNodeOfClass(classNode, "findFuels", "()V");
            System.out.println("Patching FurnaceRecipeHandler");

            InsnList toInject = new InsnList();
            toInject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(Hooks.class), "findFuels", "()V", false));
            toInject.add(new InsnNode(Opcodes.RETURN));

            method.instructions.insertBefore(findFirstInstruction(method), toInject);

            return writeClassToBytes(classNode);

        } else if (name.equals("net.minecraft.item.ItemStack")) {
            boolean isObfuscated = !name.equals(transformedName);
            System.out.println(name);
            ClassNode classNode = readClassFromBytes(basicClass);
            MethodNode method = findMethodNodeOfClass(classNode, isObfuscated ? "a" : "func_150996_a", isObfuscated ? "(ace;)V" : "(Lnet/minecraft/item/Item;)V");

            InsnList toInject = new InsnList();
            toInject.add(new VarInsnNode(Opcodes.ALOAD, 0));
            toInject.add(new VarInsnNode(Opcodes.ALOAD, 1));
            toInject.add(new MethodInsnNode(Opcodes.INVOKESTATIC, Type.getInternalName(Hooks.class), "checkNull", "(Lnet/minecraft/item/Item;)V", false));

            method.instructions.insertBefore(getOrFindInstruction(method.instructions.getLast(), true).getPrevious(), toInject);

            return writeClassToBytes(classNode);
        }
        return basicClass;
    }

    private ClassNode readClassFromBytes(byte[] bytes) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);
        return classNode;
    }

    private MethodNode findMethodNodeOfClass(ClassNode classNode, String methodName, String methodDesc) {
        for (MethodNode method : classNode.methods) {
            if (method.name.equals(methodName) && method.desc.equals(methodDesc)) {
                return method;
            }
        }
        return null;
    }

    public AbstractInsnNode findFirstInstruction(MethodNode method) {
        return getOrFindInstruction(method.instructions.getFirst());
    }

    public AbstractInsnNode getOrFindInstruction(AbstractInsnNode firstInsnToCheck) {
        return getOrFindInstruction(firstInsnToCheck, false);
    }

    public AbstractInsnNode getOrFindInstruction(AbstractInsnNode firstInsnToCheck, boolean reverseDirection) {
        for (AbstractInsnNode instruction = firstInsnToCheck; instruction != null; instruction = reverseDirection ? instruction.getPrevious() : instruction.getNext()) {
            if (instruction.getType() != AbstractInsnNode.LABEL && instruction.getType() != AbstractInsnNode.LINE)
                return instruction;
        }
        return null;
    }

    private byte[] writeClassToBytes(ClassNode classNode) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
