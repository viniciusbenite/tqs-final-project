import React, { Component } from 'react';
import items from './data'

const CabeleireirosContext = React.createContext();

class CabeleireirosProvider extends Component {

    state = {
        cabeleireiros: [],
        sortedCabeleireiros: [],
        
        loading: true,
        nome:"",
        cidade:"",
    };
    // getData

    componentDidMount() {
        // this.getData
        let cabeleireiros = this.formatData(items);
        
    

        this.setState({
            cabeleireiros,
            
            sortedCabeleireiros: cabeleireiros,
            loading: false,
            
        });
    }

    formatData(items) {
        let tempItems = items.map(item => {
            let id = item.sys.id
            let imagens = item.fields.imagens.map(image =>
                image.fields.file.url);

            let cabeleireiro = { ...item.fields, imagens, id }
            return cabeleireiro;
        });
        return tempItems
    }
    getCabeleireiro = (slug) => {
        let tempCabeleireiros = [...this.state.cabeleireiros];
        const cabeleireiro = tempCabeleireiros.find(cabeleireiro => cabeleireiro.slug === slug);
        return cabeleireiro;
    };
handleChange = event => {
    const target = event.target;
    const value = target.nome === 'checkbox' ?
    target.checked : target.value;
    const name = event.target.name;
   this.setState({
      [name] :value 
   },
    this.filterCabeleireiros
    );
};
filterCabeleireiros = () => {
    let{
        cabeleireiros, 
        nome,
        cidade,
    } = this.state

// all the cabeleireiros
let tempCabeleireiros = [...cabeleireiros];


// filter by nome 

if(nome !== "") {
    tempCabeleireiros = tempCabeleireiros.filter(cabeleireiro => cabeleireiro.nome ===
    nome);
}



// filter by cidade
if(cidade !== "") {
    tempCabeleireiros = tempCabeleireiros.filter(cabeleireiro => cabeleireiro.cidade ===
    cidade);
}



// change state 
this.setState({
    sortedCabeleireiros:tempCabeleireiros
})
};
    render() {
        return (
        <CabeleireirosContext.Provider 
        value={{
            ...this.state,
            getCabeleireiro: this.getCabeleireiro,
            handleChange: this.handleChange
            }}>
            {this.props.children}
        </CabeleireirosContext.Provider>
            
         );
    }
  }


const CabeleireirosConsumer = CabeleireirosContext.Consumer;
export function withCabeleireirosConsumer(Component){
    return function ConsumerWrapper(props){
        return <CabeleireirosConsumer>
            {value => <Component {...props} context={value} />}
        </CabeleireirosConsumer>
    }
}

export { CabeleireirosProvider, CabeleireirosConsumer, CabeleireirosContext };