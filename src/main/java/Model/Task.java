package Model;

public class Task  {
    private static int counter;
    private int ID;
    private boolean isChecked;
    private String description;

    public Task(String description) {
        this.ID = ++counter;
        this.description = description;
    }


    public void Check(){
        if (isChecked){
            isChecked = false;
        }else{
            isChecked = true;
        }
    }

    public String getDescription() {
        return description;
    }

    public boolean isChecked() {
        return isChecked;
    }

//    @Override
//    public String toString() {
//        return "Task{" +
//                "ID=" + ID +
//                ", isChecked=" + isChecked +
//                ", description='" + description + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return description;
    }
}
