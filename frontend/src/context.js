import React, { Component } from 'react';
import getSaloes from './data';


const CabeleireirosContext = React.createContext();

class CabeleireirosProvider extends Component {

    state = {
        cabeleireiros: [],
        sortedCabeleireiros: [],
        barbeiros:[],
        sortedBarbeiros:[],
        
        loading: true,
        nome:"",
        cidade:"",
    };
    // getData

    componentDidMount() {

        var cabeleireiros=[];
        var barbeiros=[];


        getSaloes().then(data => {

        cabeleireiros = this.formatDataCabeleireiros(data);
        barbeiros = this.formatDataBarbeiros(data);

        this.setState({
            cabeleireiros:cabeleireiros,
            barbeiros:barbeiros,
            sortedBarbeiros:barbeiros,
            
            sortedCabeleireiros: cabeleireiros,
            loading: false,
            
        });
        });
    }

    formatDataCabeleireiros(items) {

        let tempItems=[];
        
        items.map(item => {
            let id = item.id;
            let nome = item.name;
            let cidade = item.city;
            let descricao=item.description;
            let morada=item.address;
            let imagens = [item.image];
            let type = item.type;
            let contacto=item.contact;

            let cabeleireiro = {nome,morada,descricao,cidade,type,imagens,id ,contacto}
            if(type=="Cabeleireiro") tempItems.push(cabeleireiro); 
            
        });
       
       
       
      
        return tempItems
    }

    formatDataBarbeiros(items) {

        let tempItems=[];
    
        items.map(item => {
            let id = item.id;
            let nome = item.name;
            let cidade = item.city;
            let descricao=item.description;
            let morada=item.address;
            let imagens = [item.image];
            let type = item.type;

            let barbeiro = {nome,morada,descricao,cidade,type,imagens,id }
            if(type=="Barbeiro") tempItems.push(barbeiro); 
            
        });




        return tempItems
    }


    getCabeleireiro = (id) => {
        let tempCabeleireiros = [...this.state.cabeleireiros];
        const cabeleireiro = tempCabeleireiros.find(cabeleireiro => cabeleireiro.id === id);
        return cabeleireiro;
    };


    getBarbeiro = (id) => {
        let tempBarbeiros = [...this.state.barbeiros];
        const barbeiro = tempBarbeiros.find(barbeiro => barbeiro.id === id);
        return barbeiro;
    };

handleChangeCabeleireiros = event => {
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


    handleChangeBarbeiros = event => {
        const target = event.target;
        const value = target.nome === 'checkbox' ?
        target.checked : target.value;
        const name = event.target.name;
    this.setState({
        [name] :value 
    },
        this.filterBarbeiros
        );
    };


    filterBarbeiros = () => {
        let{
            barbeiros, 
            nome,
            cidade,
        } = this.state

    // all the cabeleireiros
    let tempBarbeiros = [...barbeiros];


    // filter by nome 

    if(nome !== "") {
        tempBarbeiros = tempBarbeiros.filter(barbeiro => barbeiro.nome ===
        nome);
    }



    // filter by cidade
    if(cidade !== "") {
        tempBarbeiros = tempBarbeiros.filter(barbeiro => barbeiro.cidade ===
        cidade);
    }



    // change state 
    this.setState({
        sortedBarbeiros:tempBarbeiros
    })
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
            handleChangeCabeleireiros: this.handleChangeCabeleireiros,
            getBarbeiro: this.getBarbeiro,
            handleChangeBarbeiros: this.handleChangeBarbeiros,
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