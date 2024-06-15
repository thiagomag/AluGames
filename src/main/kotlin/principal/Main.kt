package principal

import modelo.Jogo
import service.ConsumoApi
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar:")
    val busca = leitura.nextLine()

    var meuJogo: Jogo? = null
    runCatching {
        meuJogo = ConsumoApi().buscaJogo(busca)
    }.onFailure {
        println("Jogo inexistente. Tente outro id.")
        return
    }.onSuccess {
        println("Deseja inserir uma descrição personalizada? S/N")
        val resposta = leitura.nextLine()
        if (resposta.equals("S", ignoreCase = true)) {
            println("Digite a descrição:")
            meuJogo?.descricao = leitura.nextLine()
        } else {
            meuJogo?.descricao = meuJogo?.titulo
        }
    }
    println(meuJogo.toString())
    println("Busca finalizada com sucesso.")
}