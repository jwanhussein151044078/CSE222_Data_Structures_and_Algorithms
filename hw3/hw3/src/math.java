/**
 * class math
 *
 */
public class math {
    /**
     * returns the absolute value of the given float number
     * @param i the number
     * @return the abs value
     */
    public static float abs(float i){
        if( i < 0 ){
            return -i;
        }
        return i;
    }

    /**
     * Returns the trigonometric sin of an angle
     * @param n an angle
     * @return the sin of the argument.
     */
    public static float sin(float n){
        float denominator, sinx;
        n = n * (float)(3.142 / 180.0);
        float x1 = n;
        sinx = n;
        int i = 1;
        do
        {
            denominator = 2 * i * (2 * i + 1);
            x1 = -x1 * n * n / denominator;
            sinx = sinx + x1;
            i = i + 1;
        } while (i < 100);
        return sinx;
    }
    /**
     * Returns the trigonometric cosine of an angle
     * @param n an angle
     * @return the cosine of the argument.
     */
    public static float cos(float n){
        return sin(90-n);
    }
}
