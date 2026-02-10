public class Hospede {
    private int id;
    private String nome;
    private String documento;

    public Hospede(int id, String nome, String documento) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
    }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDocumento(String documento) { this.documento = documento; }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDocumento() { return documento; }

    @Override
    public String toString() {
        return "Hospede: " +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'';
    }
}
