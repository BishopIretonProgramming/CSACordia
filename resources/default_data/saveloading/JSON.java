package resources.default_data.saveloading;

class JSON {
    final String title;

    protected JSON(String title) {
        this.title = title;
    }    

    public static String getTitle(JSON head) {
        return head.title;
    }
}