public class Main {
    public static void main(String[] args) {
        //set up MVC
        User model = new User();
        View view = new View();
        Controller controller = new Controller(model, view);

        //start up system
        controller.updateLoginView();
    }
}
