import java.util.Comparator;
import java.util.Iterator;

public class NLNode <T>{
    // Instance Varaibles
    private NLNode<T> parent;
    private ListNodes<NLNode<T>> children;
    private T data;

    // Constructors
    public NLNode(){
        this.parent = null;
        this.data = null;
        this.children = new ListNodes<NLNode<T>>();

    }

    public NLNode (T d, NLNode<T> p){
        this.parent = p;
        this.data = d;
        this.children = new ListNodes<NLNode<T>>();
    }

    // Sets the parent value
    public void setParent(NLNode<T> p){
        this.parent = p;
    }

    // Returns the parent value
    public NLNode<T> getParent(){
        return this.parent;
    }

    // Adds a child to the nodes children list
    public void addChild(NLNode<T> newChild){
        this.children.add(newChild);
        newChild.setParent(this);
    }

    // Returns the nodes children
    public Iterator<NLNode<T>> getChildren(){
        return this.children.getList();
    }

    // Returns the nodes children in sorted manner depending on the inputted sorter
    public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter){
        return this.children.sortedList(sorter);
    }

    // Return the nodes data
    public T getData(){
        return this.data;
    }

    // Sets the nodes data to the inputted value
    public void setData(T d){
        this.data = d;
    }

}
