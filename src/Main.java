
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) {

        DirectoryElement root = FileSystemFactory.sample();

        // Calculate the total file size
        SizeCalculatorVisitor sizeVisitor = new SizeCalculatorVisitor();
        root.accept(sizeVisitor);

        System.out.println(
                "Total size: " + sizeVisitor.getTotalSize() + " bytes"
        );

        // Search for Java files
        SearchVisitor searchVisitor = new SearchVisitor(".java");
        root.accept(searchVisitor);

        System.out.println(
                "Java files: " + searchVisitor.getMatchingFiles()
        );

        // Run independent visitors concurrently
        CompletableFuture<Long> sizeTask =
                CompletableFuture.supplyAsync(() -> {
                    SizeCalculatorVisitor visitor =
                            new SizeCalculatorVisitor();

                    root.accept(visitor);
                    return visitor.getTotalSize();
                });

        CompletableFuture<List<String>> searchTask =
                CompletableFuture.supplyAsync(() -> {
                    SearchVisitor visitor =
                            new SearchVisitor(".java");

                    root.accept(visitor);
                    return visitor.getMatchingFiles();
                });

        System.out.println(
                "Concurrent total size: " + sizeTask.join() + " bytes"
        );

        System.out.println(
                "Concurrent Java files: " + searchTask.join()
        );
    }
}
