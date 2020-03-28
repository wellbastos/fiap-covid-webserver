package br.com.fiap.billing.api.person

class PersonSummary (
    val state: String,
    val totalCases: Int = 1000,
    val totalDeaths: Int = 100,
    val totalNurses: Int,
    val totalDoctors: Int,
    val totalNursingTechnicals: Int,
    val totalMaintainers: Int,
    val totalHelpers: Int) {
}