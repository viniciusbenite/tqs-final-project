import React from 'react'

import { withCabeleireirosConsumer} from '../context';
import Loading from './Loading';
import BarbeirosFilter from './BarbeirosFilter';
import BarbeirosList from './BarbeirosList';


function BarbeirosContainer ({context}){
    const {loading, sortedBarbeiros, barbeiros} = context;

    if (loading) {
        return <Loading />;
    }
        return (
            <>
               
                <BarbeirosFilter  barbeiros={ barbeiros} />
                <BarbeirosList  barbeiros={sortedBarbeiros}/>
            </>
        );
    }
export default withCabeleireirosConsumer(BarbeirosContainer);



