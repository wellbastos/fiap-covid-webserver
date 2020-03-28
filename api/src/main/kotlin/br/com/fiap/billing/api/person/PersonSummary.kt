package br.com.fiap.billing.api.person

class PersonSummary (
    val city: String,
    val state: String,
    val totalNurses: Int,
    val totalDoctors: Int,
    val totalNursingTechnicals: Int,
    val totalMaintainers: Int,
    val totalHelpers: Int) {
}