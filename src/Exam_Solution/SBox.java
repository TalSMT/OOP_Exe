package Exam_Solution;
/**
 * This is a demo question for Final exam - it is surely too long - yet with parts
 * of the code it can be seen as a possible class / code writing question.
 */
import java.util.Date;

public class SBox implements safeBox{
	public static final double LOCK_TIME = 1000*60*1;
	public static final double LOCK_3_FALSE_TIME = 1000*60*15;
	private Date _last_time, _last_lock;
	private String _key;
	private double _balance;
	private int _num_of_false;
	private boolean _isOpen = false;

	public SBox(String k) {
		_key = k;
		_num_of_false = 0;
		set_last_time();
	}
	public boolean open(String key) {
		if(key.equals(_key)) {
			if(this._last_lock==null || _last_lock.getTime()+LOCK_3_FALSE_TIME < new Date().getTime()) {
				set_last_time();
				_isOpen = true;
			}
		}
		else {
			_num_of_false ++;
			if(_num_of_false ==3) {
				_num_of_false = 0;
				_last_lock = new Date();
				this.lock();
				System.err.println("ERR: SafeBox is now locked for 15 m'");
			}
		}
		return this._isOpen;
	}

	public boolean lock() {
		this._isOpen = false;
		return this._isOpen;
	}

	public double balance() {
		if(isOpen()) {
			return this._balance;
		}
		else {
			throw new RuntimeException("ERR the safe box is closed");
		}
	}

	public double deposit(double n) {
		if(isOpen() && n>0 ) {
			this._balance += n;
		}
		return this.balance();
	}

	public double withdraw(double n) {
		if(isOpen() && n>0 ) {
			if(n<=this.balance()) {
				this._balance -= n;
			}
		}
		return this.balance();
	}
	public boolean isOpen() {
		boolean ans = false;
		Date curr = new Date();
		if(this._isOpen && !isLocked() && curr.getTime() < this._last_time.getTime() + LOCK_TIME) {
			set_last_time();
			ans = true;
		}
		else {
			_isOpen = false;
		}
		return _isOpen;
	}
	
	public String toString() {
		return "Sbox: open="+this.isOpen()+"  , balance="+this._balance;
	}
	//////////// private ///////////////////
	private void set_last_time() {
		this._last_time = new Date();
	}
	private boolean isLocked() {
		boolean ans = false;
		if(this._last_lock!=null && new Date().getTime() > _last_lock.getTime()+LOCK_3_FALSE_TIME) {
			ans = true;
		}
		return ans;
	}
}
