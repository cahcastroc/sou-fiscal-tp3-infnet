package br.edu.infnet.soufiscal.model


data class Avaliacao(
    var id: String? = null,
    var avaliador: String? = null,

    var nome: String? = null,
    var bairro: String? = null,
    var limpeza: String? = null,
    var organizacao: String? = null,
    var validadeInsumos: String? = null,
    var documentacao: String? = null,
    var controlePragas: String? = null,
    var refrigeracao: String? = null,
)
