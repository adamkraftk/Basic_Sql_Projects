public class Books {
    //Make the books into objects
    private int id;
    private String Title;
    private String Author;
    private int Qty;

    public Books(int id, String Title, String Author, int Qty){
        this.id = id;
        this.Title = Title;
        this.Author = Author;
        this.Qty = Qty;
    }

    public String toString() {
        return id + ", " + Title + ", " + Author + ", " + Qty + ".\n";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public int getQty() {
        return Qty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setQty(int qty) {
        Qty = qty;
    }
}
