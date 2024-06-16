package br.com.fiap.challenge.filtros

import br.com.fiap.challenge.R
import br.com.fiap.challenge.landingpage.EmailClass
import br.com.fiap.challenge.listaEmails.Email

class Filtros {
}

fun getAllEmails(): List<Email> {
    return   listOf(
        Email("Joana Ferniz", "Prazo para edição das fotos", "Em até 2 dias estará pronto!", R.drawable.ic_avatar1, false),
        Email("Lucas Freire", "Faturamento mês XX", "Bom Dia! Faturamento do mês XX...", R.drawable.ic_avatar2, false),
        Email("Paulo Lima", "Doc em anexo", "Ok, Muito Obrigada. Atenciosamente...", R.drawable.ic_avatar3, false),
        Email("Kar Custon", "Itens automobilísticos", "Oi, poderia enviar o boleto no num. XXX", R.drawable.ic_avatar4, true)
    )
}

fun getEmailsByName(name: String): List<Email> {
    return getAllEmails().filter {
        it.sender.startsWith(prefix = name, ignoreCase = true)
    }
}

fun getEmailsFavoritos(): List<Email> {
    return getAllEmails().filter {
        it.favorito
    }
}

fun getAllEmailsHome():List<EmailClass> {
    return  listOf(
        EmailClass("Joana Ferniz", "Prazo para edição das fotos", "São Paulo - SP", R.drawable.ic_avatar1, false, false),
        EmailClass("Lucas Freire", "Faturamento mês XX", "João Pessoa - PB", R.drawable.ic_avatar2, false,false),
        EmailClass("Paulo Lima", "Doc em anexo", "Amazonas - AM", R.drawable.ic_avatar3, false,false),
        EmailClass("Kar Custon", "Itens automobilísticos", "Pará - PR", R.drawable.ic_avatar4, true,true)
    )
}

fun getEmailsImportantes():List<EmailClass> {
    return getAllEmailsHome().filter {
        it.isImportant
    }
}