class ProduzentKonsumentProblem {
    public static void main(String args[]) {
        int i, num_prod, num_kons;
        FIFOPuffer puffer;

        puffer = new FIFOPuffer(5);

        (new Konsument(1, puffer)).start();
        (new Konsument(2, puffer)).start();
        (new Konsument(3, puffer)).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        (new Produzent(1, puffer)).start();

    }
}
