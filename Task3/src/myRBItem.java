public class myRBItem<Type>{
   public char color;
    public myRBItem<Type> left;
    public myRBItem<Type> right;
    public int key;
    public Type value;

   public myRBItem ( myRBItem<Type> left,myRBItem<Type> right, int key, Type value, char color){
       this.left = left;
       this.right = right;
       this.key = key;
       this.value = value;
       this.color = color;
   }
}
