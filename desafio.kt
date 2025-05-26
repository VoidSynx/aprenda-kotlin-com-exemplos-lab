enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String, val age: Int, val isProfessor: Boolean = false)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val professor: Usuario, val nivel: Nivel)

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>, val nivel: Nivel) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(vararg usuarios: Usuario) {
        for (usuario in usuarios) {
            if (!usuario.isProfessor) {
                inscritos.add(usuario)
                println("Matrícula de ${usuario.nome} na formação '${this.nome}' realizada com sucesso.")
            } else {
                println("Professores não podem se matricular.")
            }
        }
    }

    override fun toString(): String {
        return "Formacao(nome='$nome', nivel=$nivel, conteudos=${conteudos.map { it.nome }}, inscritos=${inscritos.map { it.nome }})"
    }
}

fun main() {
    val professorAdmin = Usuario("Dr. Galhanone", 41, isProfessor = true)

    val aluno1 = Usuario("David balzarini", 22)
    val aluno2 = Usuario("joao", 30)
    val aluno3 = Usuario("maria", 28)

    val conteudoKotlinBasico = ConteudoEducacional("Introdução ao Kotlin", 90, professorAdmin, Nivel.BASICO)
    val conteudoCoroutines = ConteudoEducacional("Kotlin Coroutines", 120, professorAdmin, Nivel.INTERMEDIARIO)
    val conteudoPOO = ConteudoEducacional("Programação Orientada a Objetos com Kotlin", 100, professorAdmin, Nivel.INTERMEDIARIO)

    val todosOsConteudos = listOf(conteudoKotlinBasico, conteudoCoroutines, conteudoPOO)
    val formacaoKotlinCompleta = Formacao(
        nome = "Formação Completa em Kotlin",
        conteudos = todosOsConteudos,
        nivel = Nivel.AVANCADO
        
    )

    println("\nProfessor criado: ${professorAdmin.nome}")
    
    println("Alunos criados: ${listOf(aluno1, aluno2, aluno3).map { it.nome }}")
    
    println("Conteúdos criados: ${todosOsConteudos.map { it.nome }}")

    println("\nFormação criada: ${formacaoKotlinCompleta.nome} (Nível: ${formacaoKotlinCompleta.nivel})")
    println("Conteúdos da Formação: ${formacaoKotlinCompleta.conteudos.map { it.nome }}")

    println("\n--- Realizando Matrículas ---")
    formacaoKotlinCompleta.matricular(aluno1, aluno2, professorAdmin)

    println("\n--- Estado Final da Formação ---")
    println(formacaoKotlinCompleta)
}