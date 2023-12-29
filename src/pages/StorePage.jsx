import React, { useEffect, useState } from 'react'
import NavBar from '../component/NavBar'
import Footer from '../component/Footer'
import ECard from '../component/ECard'
import { getStoreList } from '../api-client/api-module';

export default function StorePage() {

  const [storeList, setStoreList] = useState();

  useEffect(() => {
    fetchData();
  }, []);



  const fetchData = async () => {
    try {
      const list = await getStoreList();
      setStoreList(list);

    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };
  return (
    <>
      <NavBar />
      <div className="body-container">
        <div className='heading-title'>Equipment Store </div>
        <div className="eq-conatainer">
          {storeList && storeList.map(item => {
            return (
              <ECard key={item.id} data={item} />
            )
          }
          )}
        </div>
      </div>
      <Footer />
    </>

  )
}
