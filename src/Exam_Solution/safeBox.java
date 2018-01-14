package Exam_Solution;

public interface safeBox {
	public boolean open(String key);
	public boolean lock();
	public double balance();
	public double deposit(double n); // returns the amount after the deposit
	public double withdraw(double n); // returns the amount after the withdraw
	public String toString(); // status(open/close), balance.
}
