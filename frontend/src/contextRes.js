import React, { Component } from 'react';
import getReservas from './dataRes';


const ReservasContext = React.createContext();

class ReservasProvider extends Component {

    state = {
        sortedReservas: [],
        loading: true,
    };
    // getData

    componentDidMount() {
        var reservas=[];

        getReservas().then(data =>{
            reservas = this.formatDataReservas(data);
            this.setState({
                sortedReservas:reservas,
                loading: false,
            })
        })
    }
    formatDataReservas(items) {

        let tempItems=[];

        items.map(item => {
            let id = item.id;
            let data = item.date;
            let hour = item.time;
            let service = item.services;

            let reserva = {data,hour,service,id};
            tempItems.push(reserva);

        });
        return tempItems
    }

    handleChangeReservas = event => {
        const target = event.target;
        const value = target.nome === 'checkbox' ?
            target.checked : target.value;
        const name = event.target.name;
        this.setState({
                [name] :value
            },
        );
    };



    render() {
        return (
            <ReservasContext.Provider
                value={{
                    ...this.state,
                    getReserva: this.getReserva,
                    handleChangeReservas: this.handleChangeReservas,
                }}>
                {this.props.children}
            </ReservasContext.Provider>

        );
    }
}

const ReservasConsumer = ReservasContext.Consumer;

export function withReservasConsumer (Component){
    return function ConsumerWrapper(props){
        return <ReservasConsumer>
            {value => <Component {...props} context={value} />}
        </ReservasConsumer>
    }
}

export { ReservasProvider, ReservasConsumer, ReservasContext };