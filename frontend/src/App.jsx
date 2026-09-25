import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, CartesianGrid } from 'recharts';

export default function App() {
  const [estados, setEstados] = useState([]);
  const [municipios, setMunicipios] = useState([]);
  const [estadoSelecionado, setEstadoSelecionado] = useState('');
  const [loading, setLoading] = useState(true);

  // Carga inicial dos estados
  useEffect(() => {
    axios.get('http://localhost:8080/api/dashboard/estados')
      .then(response => {
        setEstados(response.data);
      })
      .catch(error => console.error("Erro ao carregar estados:", error));
  }, []);

  // Carga dos municípios (filtra por estado se algum estiver selecionado)
  useEffect(() => {
    setLoading(true);
    const url = estadoSelecionado 
      ? `http://localhost:8080/api/dashboard/municipios?estadoSigla=${estadoSelecionado}`
      : 'http://localhost:8080/api/dashboard/municipios';

    axios.get(url)
      .then(response => {
        setMunicipios(response.data);
        setLoading(false);
      })
      .catch(error => {
        console.error("Erro ao carregar municípios:", error);
        setLoading(false);
      });
  }, [estadoSelecionado]);

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>Dashboard de Inteligência de Mercado</h1>
      <p>Mercado de Planos de Saúde e Seguros de Vida</p>

      {/* Filtro por Estado */}
      <div style={{ marginBottom: '20px' }}>
        <label style={{ marginRight: '10px', fontWeight: 'bold' }}>Filtrar por Estado (UF):</label>
        <select 
          value={estadoSelecionado} 
          onChange={(e) => setEstadoSelecionado(e.target.value)}
          style={{ padding: '8px', fontSize: '14px' }}
        >
          <option value="">Todos os Estados</option>
          {estados.map(e => (
            <option key={e.id} value={e.sigla}>
              {e.nome} ({e.sigla})
            </option>
          ))}
        </select>
      </div>

      {loading ? (
        <p>A carregar dados...</p>
      ) : (
        <div>
          {/* Gráfico de Visualização */}
          <h2>Visualização por Município</h2>
          <div style={{ width: '100%', height: 300, marginBottom: '40px' }}>
            <ResponsiveContainer>
              <BarChart data={municipios.slice(0, 15)}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="nome" />
                <YAxis />
                <Tooltip />
                <Bar dataKey="populacao" fill="#0088FE" name="População Estimada" />
              </BarChart>
            </ResponsiveContainer>
          </div>

          {/* Tabela de Dados */}
          <h2>Lista de Municípios ({municipios.length})</h2>
          <table border="1" cellPadding="10" cellSpacing="0" style={{ width: '100%', textAlign: 'left', borderCollapse: 'collapse' }}>
            <thead>
              <tr style={{ backgroundColor: '#f2f2f2' }}>
                <th>Código IBGE</th>
                <th>Município</th>
                <th>Estado</th>
                <th>População</th>
                <th>Potencial de Mercado</th>
              </tr>
            </thead>
            <tbody>
              {municipios.slice(0, 20).map(m => (
                <tr key={m.codigoIbge}>
                  <td>{m.codigoIbge}</td>
                  <td>{m.nome}</td>
                  <td>{m.estado ? m.estado.sigla : '-'}</td>
                  <td>{m.populacao ? m.populacao.toLocaleString('pt-BR') : 'N/A'}</td>
                  <td><strong>{m.potencialMercado}</strong></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
