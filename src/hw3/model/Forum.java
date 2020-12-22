package hw3.model;

public class Forum {
    private int id;
    private String name;
    private int size;

    public Forum(int id, String name) {
        this.id = id;
        this.name = name;
        this.size = 0;
    }

    public Forum(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }
}