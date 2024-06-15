package modelo

class Jogo(val titulo:String,
           val capa:String) {
    var descricao:String? = null

    override fun toString(): String {
        return "Meu modelo.Jogo: \n" +
                "Título: $titulo \n" +
                "Capa: $capa \n" +
                "Descricao: $descricao"
    }
}