package principal

import modelo.Gamer
import modelo.Jogo
import service.ConsumoApi
import utilitario.transformarEmIdade
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluído com sucesso. Dados do gamer:")
    println(gamer)
    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())

    do {
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
        gamer.jogosBuscados.add(meuJogo)
        println("Deseja buscar outro jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("S", ignoreCase = true))

    println("Jogos buscados:")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por título:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Título: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    println("\n Jogos filtrados: ")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if (opcao.equals("s", true)) {
        println(gamer.jogosBuscados)
        println("\n Informe a posição do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
        println("\n Lista atualizada:")
        println(gamer.jogosBuscados)
    }

    println("Busca finalizada com sucesso.")

}