/**
 *
 */


/**
 * <p>蟻のフェロモンを表す<b>抽象クラス</b>。</p>
 * <p>その実態は0以上100以下の変域を変化する成分からなるベクトルである。
 * また、各次元に名前を付けることもできる。</p>
 * created on 2020/03/15
 * @author <a href=http://github.com/17ec084>Tomotaka Hirata(17ec084)</a>
 *
 */
public abstract class Pheromone
{
	/**
	 * フェロモンベクトル。外部から自由に書き換えできるよう、<b>public宣言している</b>。
	 */
	public double[] vector;
	protected double max;
	final protected double min = 0;
	protected Colony colony;
	protected Symbol symbol;

	/**
	 * フェロモンベクトルの次元数
	 */
	abstract public int get_DIM();

	public void set_max(double max){this.max = max;}
	public double get_max(){return this.max;}

	public void set_colony(Colony colony){this.colony = colony; this.symbol = colony.get_pheromone_symbol();}

	/**
	 * フェロモンベクトルの、指定された成分が0～maxの範囲を逸脱していないか確かめる
	 * @param symbol <p>インデックス整数あるいはそのシンボル名(Symbol型。コロニー全体で共有)
	 * ただしオーバロードしてあるため、int型やString型で与えても問題ない</p>
	 */
	public boolean check_range(Symbol symbol){try{this.get_param(symbol);}catch(OutOfRangeException p){return false;}return true;}
	public boolean check_range(int index){return check_range(this.symbol.from(index));}
	public boolean check_range(String str){return check_range(this.symbol.from(str));}

	/**
	 * フェロモンベクトルのすべての成分に対して、check_rangeメソッドを適用する。
	 * @see #check_range
	 * @return 逸脱が全くないなら-1、その他の場合、逸脱のある最小のインデックス
	 */
	public int check_range_all(){for(int i=0; i<this.vector.length; i++)if(!check_range(i))return i;return -1;}

	public boolean check_dim(){return this.vector.length==this.get_DIM();}

	public void check() throws PheromoneException
	{
		if(check_range_all()!=-1)throw new OutOfRangeException(check_range_all());
		if(!check_dim())throw new DIMException();
	}

	public double get_param(Symbol symbol) throws OutOfRangeException
	{
		double rtn = this.vector[symbol.get_idx()];
		if(rtn < 0 || this.max < rtn) throw new OutOfRangeException();
		return rtn;
	}

	public class PheromoneException extends Exception{public PheromoneException(String msg){super(msg);} public PheromoneException() {super();}}
	public class OutOfRangeException extends PheromoneException{public OutOfRangeException(int i){super("フェロモンベクトルの"+i+"番目が範囲逸脱を起こしています。");} public OutOfRangeException(){super();}}
	public class DIMException extends PheromoneException{}
}


