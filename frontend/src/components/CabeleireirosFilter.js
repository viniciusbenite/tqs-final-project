import React from 'react'
import {useContext} from 'react';
import {CabeleireirosContext} from '../context';
import Title from './Title';
// get all unique values\
const getUnique = (items, value) => {
    return [...new Set(items.map(item => item[value]))]
}
export default function CabeleireirosFilter({cabeleireiros}) {
const context = useContext(CabeleireirosContext);
const {
    handleChangeCabeleireiros,
    nome,
    cidade,
  
} = context;
// Get unique types


let nomes = getUnique(cabeleireiros, 'nome');

// add all
nomes = ["todos", ...nomes];
// map to jsx
nomes = nomes.map((item, index)=>{
    return( 
    <option value={item} key={index}>
    {item}
    </option>
);
});

let cidades = getUnique(cabeleireiros, 'cidade');

cidades = ["todas", ...cidades];
// map to jsx
cidades = cidades.map((item, index)=>{
    return( 
    <option value={item} key={index}>
    {item}
    </option>
);
});


    return (
    <section className="filter-container">
        <Title title="procure por nome ou cidade" />
        <form className="filter-form">

        <div className="form-group">
<label htmlFor="nome">Nome</label>
<div className="search-inputs">
 <input type="String" name="nome" id="nome"
 value={nome} onChange={handleChangeCabeleireiros}
 
 className="search-input"/>  
</div>
</div>

<div className="form-group">
<label htmlFor="cidade">cidade</label>
<div className="cidade-inputs">
 <input type="String" name="cidade" id="cidade"
 value={cidade} onChange={handleChangeCabeleireiros}
 
 className="search-input"/>  
</div>
</div>





</form>
</section>
);  
}
