package behavioral.strategy;

public interface SimpleQuack extends IQuackBehavior{
    //@Override
    default void quack() {
        System.out.println("SimpleQuack Iquack");
    };
}
