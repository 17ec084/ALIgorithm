
public final class Biased_pheromone extends Pheromone
{

	public Biased_pheromone(int DIM, double max, Symbol symbol)
	{
		//バイアス済で-max～maxなので、非バイアスでは0～2max
		super(null, DIM, /*max = */2*max, /*min = */0, symbol);
	}

	@Override //非バイアスの各要素(0～2max)は、バイアス済(-max～max)にmaxを加えて得られる
	public void set_vector(double[] vector){super.vector = java.util.Arrays.stream(vector).map(d -> d+super.max).toArray();}
	@Override
	public double[] get_vector(){return java.util.Arrays.stream(super.vector).map(d -> d-super.max).toArray();}

}
