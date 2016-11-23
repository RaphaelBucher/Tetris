/* 
 * Tetris Clone
 * (c) Raphael Bucher 2014
 * */
package tetris;

/**
 * An intervall-timer utility-class.
 */
public class Timer {
  private int intervallMillis;
  private long lastRecordedTime;
  private boolean startsInstant;

  /**
   * @param intervallMillis
   *          intervallSurpassed() will return true once every these millis.
   * @param startsInstant
   *          If set true, intervallSurpassed() will return true at its first
   *          call, else it will return true for the first time after the
   *          intervall has passed.
   */
  public Timer(int intervallMillis, boolean startsInstant) {
    assert intervallMillis >= 1;

    this.intervallMillis = intervallMillis;
    this.startsInstant = startsInstant;
  }

  private void reset() {
    lastRecordedTime = System.currentTimeMillis();
  }

  /**
   * Return true if more time elapsed than the registered intervallMillis. Note:
   * Falls die Methode zum ersten mal aufgerufen wird auch gleich die recorded
   * Timer zurückgesetzt.
   */
  public boolean intervallSurpassed() {
    if (lastRecordedTime == 0) { // will only evaluate true in case of the first
                                 // call of this method
      reset();
      if (startsInstant)
        return true;
    }

    long timeElapsed = System.currentTimeMillis() - lastRecordedTime;
    if (timeElapsed >= intervallMillis) {
      reset();
      return true;
    } else
      return false;
  }

  public boolean getStartsInstant() {
    return startsInstant;
  }
}
