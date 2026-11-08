import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class SearchVisitor implements FileSystemVisitor {

    private final String keyword;
    private final List<String> matchingFiles = new ArrayList<>();

    public SearchVisitor(String keyword) {
        Objects.requireNonNull(keyword, "Keyword cannot be null");

        this.keyword = keyword.toLowerCase(Locale.ROOT);
    }

    @Override
    public void visitFile(FileElement file) {
        String fileName = file.getName().toLowerCase(Locale.ROOT);

        if (fileName.contains(keyword)) {
            matchingFiles.add(file.getName());
        }
    }

    @Override
    public void visitDirectory(DirectoryElement directory) {
        for (FileSystemElement element : directory.getChildren()) {
            element.accept(this);
        }
    }

    public List<String> getMatchingFiles() {
        return List.copyOf(matchingFiles);
    }
}
