
public class SizeCalculatorVisitor implements FileSystemVisitor {

    private long totalSize = 0;

    @Override
    public void visitFile(FileElement file) {
        totalSize = Math.addExact(totalSize, file.getSize());
    }

    @Override
    public void visitDirectory(DirectoryElement directory) {
        for (FileSystemElement element : directory.getChildren()) {
            element.accept(this);
        }
    }

    public long getTotalSize() {
        return totalSize;
    }
}
