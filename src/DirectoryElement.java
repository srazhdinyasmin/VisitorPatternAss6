
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirectoryElement implements FileSystemElement {

    private final String name;
    private final List<FileSystemElement> children;

    public DirectoryElement(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Directory name cannot be empty"
            );
        }

        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void add(FileSystemElement element) {
        Objects.requireNonNull(element, "Element cannot be null");

        if (element == this) {
            throw new IllegalArgumentException(
                    "Directory cannot contain itself"
            );
        }

        children.add(element);
    }

    public List<FileSystemElement> getChildren() {
        return List.copyOf(children);
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitDirectory(this);
    }
}
