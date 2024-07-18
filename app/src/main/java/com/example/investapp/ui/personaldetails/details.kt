data class PersonalDetailsRequest(
    val surname: String,
    val middleName: String,
    val firstName: String,
    val dateOfBirth: String,
    val idPassportNo: String,
    val nationality: String,
    val email: String,
    val postalAddress: String,
    val accountName: String,
    val accountNumber: String,
    val accountType: String,
    val bankName: String,
    val branch: String,
    val bankCode: String,
    val phoneNumber:String,
    val name: String,
    val relationship: String,
    val id: String,
    val telephone: String,
    val idPicture: IdPicture,
    val nextOfKin: NextOfKin


)

data class IdPicture(
    val front: String,
    val back: String
)

data class NextOfKin(
    val name: String,
    val relationship: String,
    val id: String,
    val telephone: String
)
