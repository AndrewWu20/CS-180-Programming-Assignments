public class State {
    private String capitol;
    private int population;

    public State(String capitol, int population) {
        this.capitol = capitol;
        this.population = population;
    }

    public void setCapitol(String capitol) {
        this.capitol = capitol;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCapitol() {
        return capitol;
    }

    public int getPopulation() {
        return population;
    }
}
