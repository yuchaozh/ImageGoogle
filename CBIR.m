function simrank = CBIR(imgname,numofresults)
    img = imread(imgname);
    load('histolib.mat');
    tempsim = [];
    simrank = [];
    simnode.name = '';
    simnode.sim = 0;
    [m1,n1,k] = size(img);

    m = size(histolib);
    histogram = histo (img);
    for i = 1:1:m(1,1)
        similarity = calsim (histogram,m1*n1*3,histolib(i));
        simnode.name = histolib(i).name;
        simnode.sim = similarity;
        tempsim = cat(1,tempsim,simnode);
    end
    tempsim = selectsort(tempsim);
    for i=1:1:numofresults
        simrank = cat(1,simrank,tempsim(i));
    end
    %cd('test image');
    %for i=1:1:numofresults
     %   figure;
     %  imshow(simrank(i).name);
    %end
    %clear;
    %cd('..');
end

function tempsim = selectsort (tempsim)
    m = size(tempsim);
    for i =1:1:m(1,1)
        maxkey = tempsim(i).sim;
        max_index = i;
        for j=i:1:m(1,1)
            if tempsim(j).sim > maxkey
                maxkey = tempsim(j).sim;
                max_index = j;
            end
        end
        if max_index ~= i
            temp = tempsim(max_index);
            tempsim(max_index) = tempsim(i);
            tempsim(i) = temp;
        end
    end
end

function histogram = histo (img)
    histogram = zeros(64,64,64);
    [m,n,k] = size (img);
    img_r = img(:,:,1);
    img_g = img(:,:,2);
    img_b = img(:,:,3);
    for i=1:1:m
        for j=1:1:n
            r = img_r(i,j);
            g = img_g(i,j);
            b = img_b(i,j);
            index_r = fix(double(r)/4);
            index_g = fix(double(g)/4);
            index_b = fix(double(b)/4);
            histogram(index_r+1,index_g+1,index_b+1) = histogram(index_r+1,index_g+1,index_b+1) + 1;           
        end
    end
    histogram = histogram / (m*n*3);
end

function sim = calsim(imghisto,img1size,libhistonode)
    sim = 0;
    libhisto = libhistonode.colorhisto;
    img2size = libhistonode.size;
    for i=1:1:64
        for j=1:1:64
            for k=1:1:64
                sim = sim + min(imghisto(i,j,k)*img1size,libhisto(i,j,k)*img2size);
                %sim = sim + (imghisto(i,j,k)*img1size - libhisto(i,j,k)*img2size) ^ 2;
            end
        end
    end
    sim = sim / min(img1size,img2size);
    %sim = sqrt(sim);
end