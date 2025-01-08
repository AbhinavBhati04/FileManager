import java.io.File;
import java.util.ArrayList;
import  java.util.Iterator;
public class FileStructure {
    //Instance Variables
    private NLNode<FileObject> root;

    // Constructor
    // Uses the recurTree method to link all nodes in the FileStructure using the original input as the root
    public FileStructure(String fileObjectName) throws FileObjectException {
        try { // In case something goes wrong
            FileObject newFile = new FileObject(fileObjectName);
            this.root = new NLNode<FileObject>();
            this.root.setData(newFile);
            if (newFile.isDirectory()) {
                recurTree(root);
            }

        } catch (FileObjectException e) {
            throw new FileObjectException(" Error Creating File");
        }
    }

    // Recurring method that goes through each folder and links its children to it
    public void recurTree(NLNode<FileObject> r) {
        if (r.getData().isDirectory()) {
            FileObject f = r.getData();
            Iterator<FileObject> files = f.directoryFiles();
            while (files.hasNext()) {
                NLNode<FileObject> n = new NLNode<FileObject>();
                n.setData(files.next());
                r.addChild(n);
                n.setParent(r);
                recurTree(n);
            }
        }
    }

    // Returns the root of this FileStructure
    public NLNode<FileObject> getRoot() {
        return this.root;
    }

    // Returns the paths of all files with the inputted type
    public Iterator<String> filesOfType(String type) {
        ArrayList<String> paths = new ArrayList<String>();
        recurIterator(root, type, paths);
        Iterator<String> pathsIterator = paths.iterator();
        return pathsIterator;
    }

    // Recurring method that goes through every file in the structure to check if it is of the correct type, adding accordingly
    public void recurIterator(NLNode<FileObject> r, String type, ArrayList<String> paths) {
        if (r.getData().isFile()) {
            FileObject f = r.getData();
            if (f.getLongName().endsWith(type)) {
                paths.add(f.getLongName());
            }
        } else if (r.getData().isDirectory()) {
            Iterator<NLNode<FileObject>> children = r.getChildren();
            while (children.hasNext()) {
                NLNode<FileObject> n = children.next();
                recurIterator(n, type, paths);
            }
        }
    }

    // Returns the path for the first file found with the inputted name, if not found returns a blank string
    public String findFile(String name) {
        ArrayList<String> output = new ArrayList<String>();
        output.add("");
        recurName(this.root, name, output);
        String path = output.get(0);
        return path;
    }

    // Recurring method that goes through each folder and file until it finds the target file, if not found returns empty
    public void recurName(NLNode<FileObject> r, String name, ArrayList<String> output) {
        if (r.getData().getName().equals(name)) {
            output.remove(0);
            output.add(r.getData().getLongName());
        }
        else if (r.getData().isDirectory()){
            Iterator<NLNode<FileObject>> children = r.getChildren();
            while (children.hasNext()) {
                NLNode<FileObject> n = children.next();
                recurName(n, name, output);
            }
        }
    }

}
