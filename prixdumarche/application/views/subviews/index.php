<?
/*
 * author:Lee
 * title: Search View
 * Description:This page contains the index view which is a subview of the main layout.
 */
?>
<center>
    <section >
        <div class="span9">

            <div>
                <!--               //form_open('search/product_search', $attributes = array('class="form-search"'))-->
                <form class="form-search" action="search/product_search">
                    <div class = "input-append">
                        <input type = "text" class = "span8 search-query" name = "keyword">
                        <button type = "submit" class = "btn btn-primary">Search</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</center>