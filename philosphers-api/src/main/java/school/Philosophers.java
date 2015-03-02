package school;

public interface Philosophers {

    String GET_PHIL();

    String ASK_PHIL(String philosopher, String question);

    void FIRE_PHIL(String philosopher);
}
