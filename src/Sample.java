

public class Sample {
    private float hkg_avg_ct;
    private float gio_avg_ct;
    private float detla_ct;
    private float delta_delta_ct;
    private double result;

    public float getHkg_avg_ct() {
        return hkg_avg_ct;
    }

    public void setHkg_avg_ct(float hkg_avg_ct) {
        this.hkg_avg_ct = hkg_avg_ct;
    }

    public void setGio_avg_ct(float gio_avg_ct) {
        this.gio_avg_ct = gio_avg_ct;
    }

    public float getGio_avg_ct() {
        return gio_avg_ct;
    }

    public float getDetla_ct() {
        return detla_ct;
    }

    public float getDelta_delta_ct() {
        return delta_delta_ct;
    }

    public double getResult() {
        return result;
    }

    public Sample() {
    }

    //overloading
    public Sample(float hkg_avg_ct, float gio_avg_ct) {
        this.hkg_avg_ct = hkg_avg_ct;
        this.gio_avg_ct = gio_avg_ct;
    }

    public void setDetlaCt() {
        this.detla_ct = gio_avg_ct - hkg_avg_ct;
    }

    public void setDeltaDeltaCt(Sample other) {
        this.delta_delta_ct = this.detla_ct - other.detla_ct;
    }

    public void setResult() {
        this.result = Math.pow(2, -1.0 * (double) (this.delta_delta_ct));
    }

    @Override
    public String toString() {
        String gio=String.format("%.2f", this.gio_avg_ct );
        String hgk=String.format("%.2f", this.hkg_avg_ct);
        String delta=String.format("%.2f", this.detla_ct);
        String delta_delta=String.format("%.2f", this.delta_delta_ct);
        String result=String.format("%.2f", this.result);
        return gio+","+hgk+","+delta+","+delta_delta+","+result;
    }
}
