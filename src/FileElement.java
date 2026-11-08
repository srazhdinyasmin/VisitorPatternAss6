
public class FileElement implements FileSystemElement {

    private final String name;
    private final long size;

    public FileElement(String name, long size) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "File name cannot be empty"
            );
        }

        if (size < 0) {
            throw new IllegalArgumentException(
                    "File size cannot be negative"
            );
        }

        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }
}
