package Thread1;

class Thread1 {
    public static void main(String[] args) {
        Thread tic = Thread.currentThread();
        tic.setName("Thread in esecuzione");
        System.out.println("ecco il thread in corso " +tic);
        try{
            for (int i=3; i >0; i--){
                System.out.println(i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e ){
            System.out.println("mi hanno interrotto ");
        }
        System.out.println("fine del lavoro");
    }
}    

