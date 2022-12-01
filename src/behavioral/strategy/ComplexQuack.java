package behavioral.strategy;

public interface ComplexQuack extends IQuackBehavior{
  //  @Override
    default void quack() {
        System.out.println("Complex IQuack");
    };
}
