
import java.util.List;

public class VisitorTests {

    public static void main(String[] args) {

        testTotalSize();
        testFileSearch();
        testEmptyDirectory();
        testNoMatchingFiles();
        testNestedDirectories();

        System.out.println("All 5 tests passed.");
    }

    private static void testTotalSize() {
        DirectoryElement root = FileSystemFactory.sample();

        SizeCalculatorVisitor visitor = new SizeCalculatorVisitor();
        root.accept(visitor);

        assertEquals(550L, visitor.getTotalSize());
    }

    private static void testFileSearch() {
        DirectoryElement root = FileSystemFactory.sample();

        SearchVisitor visitor = new SearchVisitor(".JAVA");
        root.accept(visitor);

        assertEquals(
                List.of("Main.java", "Test.java"),
                visitor.getMatchingFiles()
        );
    }

    private static void testEmptyDirectory() {
        DirectoryElement empty = new DirectoryElement("Empty");

        SizeCalculatorVisitor visitor = new SizeCalculatorVisitor();
        empty.accept(visitor);

        assertEquals(0L, visitor.getTotalSize());
    }

    private static void testNoMatchingFiles() {
        DirectoryElement root = FileSystemFactory.sample();

        SearchVisitor visitor = new SearchVisitor(".pdf");
        root.accept(visitor);

        assertEquals(List.of(), visitor.getMatchingFiles());
    }

    private static void testNestedDirectories() {
        DirectoryElement root = new DirectoryElement("Root");
        DirectoryElement folder = new DirectoryElement("Folder");

        folder.add(new FileElement("example.txt", 100));
        root.add(folder);

        SizeCalculatorVisitor visitor = new SizeCalculatorVisitor();
        root.accept(visitor);

        assertEquals(100L, visitor.getTotalSize());
    }

    private static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError(
                    "Expected: " + expected + ", but got: " + actual
            );
        }
    }
}
