import React from 'react'
import CabeleireirosFilter from './CabeleireirosFilter';
import CabeleireirosList from './CabeleireirosList';
import { withCabeleireirosConsumer} from '../context';
import Loading from './Loading';



function CabeleireirosContainer ({context}){
const {loading, sortedCabeleireiros, cabeleireiros} = context;

if (loading) {
    return <Loading />;
}
    return (
        <>
           
            <CabeleireirosFilter  cabeleireiros={ cabeleireiros} />
            <CabeleireirosList  cabeleireiros={sortedCabeleireiros}/>
        </>
    );
}
export default withCabeleireirosConsumer(CabeleireirosContainer);



