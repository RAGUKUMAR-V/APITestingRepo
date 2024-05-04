package Pojo;

public class Api {

    private String courseTitle;

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getPrice() {
        return price;
    }

    private String price;
}
