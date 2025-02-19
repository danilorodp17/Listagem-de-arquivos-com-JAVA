document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementById("tabelaEstados")) {
        listarEstados();
        const ordenarEstadosBtn = document.getElementById("ordenarEstados");
        const filtrarEstadosBtn = document.getElementById("filtrarEstados");

        if (ordenarEstadosBtn) {
            ordenarEstadosBtn.addEventListener("click", listarEstadosOrdenados);
        }
        if (filtrarEstadosBtn) {
            filtrarEstadosBtn.addEventListener("click", filtrarEstadosPorNome);
        }
    }

    if (document.getElementById("tabelaMunicipios")) {
        listarMunicipios();
        const ordenarMunicipiosBtn = document.getElementById("ordenarMunicipios");
        const filtrarMunicipiosBtn = document.getElementById("filtrarMunicipios");

        if (ordenarMunicipiosBtn) {
            ordenarMunicipiosBtn.addEventListener("click", listarMunicipiosOrdenados);
        }
        if (filtrarMunicipiosBtn) {
            filtrarMunicipiosBtn.addEventListener("click", filtrarMunicipiosPorNome);
        }
    }

    if (document.getElementById("tabelaPaises")) {
        listarPaises();
        const ordenarPaisesBtn = document.getElementById("ordenarPaises");
        const filtrarPaisesBtn = document.getElementById("filtrarPaises");

        if (ordenarPaisesBtn) {
            ordenarPaisesBtn.addEventListener("click", listarPaisesOrdenados);
        }
        if (filtrarPaisesBtn) {
            filtrarPaisesBtn.addEventListener("click", filtrarPaisesPorNome);
        }
    }
});

function listarEstados() {
    fetch("/api/estados")
        .then(response => verificarErro(response))
        .then(data => preencherTabela("tabelaEstados", data, formatarEstado))
        .catch(error => tratarErro("Erro ao carregar estados", error));
}

function listarEstadosOrdenados() {
    fetch("/api/estados/ordenados")
        .then(response => verificarErro(response))
        .then(data => preencherTabela("tabelaEstados", data, formatarEstado))
        .catch(error => tratarErro("Erro ao carregar estados ordenados", error));
}

function filtrarEstadosPorNome() {
    const nomeParcial = document.getElementById("nomeEstadoFiltro").value;
    fetch(`/api/estados/filtrar?nomeParcial=${encodeURIComponent(nomeParcial)}`)
        .then(response => verificarErro(response))
        .then(data => preencherTabela("tabelaEstados", data, formatarEstado))
        .catch(error => tratarErro("Erro ao filtrar estados", error));
}

function listarPaises() {
    fetch("/api/paises")
        .then(response => verificarErro(response))
        .then(data => preencherTabela("tabelaPaises", data, formatarPais))
        .catch(error => tratarErro("Erro ao carregar países", error));
}

function listarPaisesOrdenados() {
    fetch("/api/paises/ordenados")
        .then(response => verificarErro(response))
        .then(data => preencherTabela("tabelaPaises", data, formatarPais))
        .catch(error => tratarErro("Erro ao carregar países ordenados", error));
}

function filtrarPaisesPorNome() {
    const nomeParcial = document.getElementById("nomePaisFiltro").value;
    fetch(`/api/paises/filtrar?nomeParcial=${encodeURIComponent(nomeParcial)}`)
        .then(response => verificarErro(response))
        .then(data => preencherTabela("tabelaPaises", data, formatarPais))
        .catch(error => tratarErro("Erro ao filtrar países", error));
}

function listarMunicipios() {
    fetch("/api/municipios")
        .then(response => verificarErro(response))
        .then(data => preencherTabela("tabelaMunicipios", data, formatarMunicipio))
        .catch(error => tratarErro("Erro ao carregar municípios", error));
}

function listarMunicipiosOrdenados() {
    fetch("/api/municipios/ordenados")
        .then(response => verificarErro(response))
        .then(data => preencherTabela("tabelaMunicipios", data, formatarMunicipio))
        .catch(error => tratarErro("Erro ao carregar municípios ordenados", error));
}

function filtrarMunicipios() {
    const nomeParcial = document.getElementById("filtroMunicipio").value.trim();

    if (nomeParcial.length === 0) {
        alert("Digite um nome de município para filtrar.");
        return;
    }

    fetch(`/api/municipios/filtrar?nomeParcial=${encodeURIComponent(nomeParcial)}`)
        .then(response => response.json())
        .then(data => {
            const tabela = document.getElementById("tabelaMunicipios").getElementsByTagName('tbody')[0];
            tabela.innerHTML = ""; // Limpa a tabela antes de preencher

            data.forEach(municipio => {
                const row = tabela.insertRow();
                row.insertCell(0).innerText = municipio.id;
                row.insertCell(1).innerText = municipio.nome;
                row.insertCell(2).innerText = municipio.estado.nome;
            });
        })
        .catch(error => console.error("Erro ao filtrar municípios:", error));
}


function preencherTabela(tabelaId, data, formatarLinha) {
    const tabela = document.getElementById(tabelaId).getElementsByTagName('tbody')[0];
    tabela.innerHTML = ""; // Limpa a tabela antes de preenchê-la
    data.forEach(item => {
        const row = tabela.insertRow();
        formatarLinha(row, item);
    });
}

function formatarEstado(row, estado) {
    row.insertCell(0).innerText = estado.id;
    row.insertCell(1).innerText = estado.nome;
    row.insertCell(2).innerText = estado.sigla;
}

function formatarMunicipio(row, municipio) {
    row.insertCell(0).innerText = municipio.id;
    row.insertCell(1).innerText = municipio.nome;
    row.insertCell(2).innerText = municipio.estado.nome;
}

function formatarPais(row, pais) {
    row.insertCell(0).innerText = pais.id;
    row.insertCell(1).innerText = pais.nome;
    row.insertCell(2).innerText = pais.sigla;
}

function verificarErro(response) {
    if (!response.ok) {
        throw new Error("Network response was not ok");
    }
    return response.json();
}

function tratarErro(mensagem, error) {
    console.error(mensagem, error);
    alert(mensagem);
}
