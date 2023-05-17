public class superPrime {

    public static void main(String[] args) {
        for(int i = 10000; i < 100000;i++){
            if(isSuperPrime(i)){
                System.out.println(i);
            }
        }
        //System.out.println(superPrime(7331));
    }
    public static boolean isPrime(int n){
        int sqrt = (int)Math.sqrt(n);
        for(int i = 2; i <= sqrt; i++){
            if(n % i == 0) return false;
        }
        return true;
    }
    public static boolean isSuperPrime(int n){

        if(n == 0) return true;
        return isPrime(n) && isSuperPrime(n / 10);
    }

}
