package br.edu.infnet.soufiscal.model



data class Avaliacao(
    var idAvaliador: String? = null,
    var nome: String? = null,
    var bairro: String? = null,
    var limpeza: Boolean? = null,
    var organizacao: Boolean? = null,
    var validadeInsumos: Boolean? = null,
    var documentacao: Boolean? = null,
    var controlePragas: Boolean? = null,
    var refrigeracao: Boolean? = null
)
