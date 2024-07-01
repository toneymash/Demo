data class EmailVerificationResponse(
    val isValid: Boolean,
    val email: String,
    val didYouMean: String?,
    val format: Boolean,
    val mx: Boolean,
    val score: Float,
    val reason: String?,
    val role: Boolean,
    val disposable: Boolean,
    val freemail: Boolean,
    val catchAll: Boolean
) {
    companion object {
        const val REASON_INVALID_EMAIL = "invalid_email"
        const val REASON_INVALID_DOMAIN = "invalid_domain"
        const val REASON_DISPOSABLE_EMAIL = "disposable_email"
        const val REASON_ROLE_EMAIL = "role_email"
        const val REASON_LOW_QUALITY = "low_quality"
    }
}