

/**
 * Pheromoneで使うクラス。フェロモンベクトルのインデックスにシンボル名を与える。<br>
 * またフィールドcurrent_indexが与えられた場合、そのインデックスを表現する。
 * created on 2020/03/15
 * @author <a href=http://github.com/17ec084>Tomotaka Hirata(17ec084)</a>
 * @see Pheromone
 */
public class Symbol
{
	private String[] symbol_names;
	private int current_index;

	public Symbol(int DIM){this.symbol_names = new String[DIM];}
	public Symbol(Pheromone p){ this(p.get_DIM());}

	public void set(int index, String symbol_name){this.symbol_names[index] = symbol_name;}

	/**
	 * 指定したインデックスを表現するSymbol型インスタンスを返却
	 * @param index
	 * @return
	 */
	public Symbol from(int index)
	{
		this.current_index = index;
		return this;
	}

	/**
	 * 指定したシンボル名を持つインデックスを表現するSymbol型インスタンスを返却
	 * @param str
	 * @return
	 */
	public Symbol from(String str)
	{
		int i = 0;
		for(String symbol_name : this.symbol_names)
		{
			if(symbol_name.equals(str))
			{
				this.current_index = i;
				return this;
			}
			i++;
		}
		return null;
	}

	public int get_idx(){return this.current_index;}

	public String get_str(){return this.symbol_names[this.current_index];}

	/**
	 * シンボル名とインデックスの対応表を返却
	 */
	public String[] get_symbol_table()
	{return java.util.Arrays.stream(this.symbol_names).map(str -> (str==null)?"undefined":str).toArray(String[]::new);}

}
