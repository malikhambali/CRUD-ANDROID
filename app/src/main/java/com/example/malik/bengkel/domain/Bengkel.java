package com.example.malik.bengkel.domain;

/**
 * Created by malik on 29/10/15.
 */
public class Bengkel {
        private String id;
        private String nama;
        private String lahir;
        private String alamat;
        private String jenisKelamin;
    private String keahlian;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNama() {

            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }
        public String getLahir() {

            return lahir;
        }


    public void setLahir(String lahir) {
        this.lahir = lahir;
    }
        public String getAlamat() {

            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getJenisKelamin() {

            return jenisKelamin;
        }

        public void setJenisKelamin(String jenisKelamin) {
            this.jenisKelamin = jenisKelamin;
        }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }
}

}
