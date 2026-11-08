
public final class FileSystemFactory {

    private FileSystemFactory() {
    }

    public static DirectoryElement sample() {
        DirectoryElement root = new DirectoryElement("Projects");

        root.add(new FileElement("notes.txt", 120));

        DirectoryElement source = new DirectoryElement("Source");
        source.add(new FileElement("Main.java", 250));
        source.add(new FileElement("Test.java", 180));

        root.add(source);

        return root;
    }
}
